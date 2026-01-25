///
/// Copyright (C) 2025 Jerome Blanchard <jayblanc@gmail.com>
///
/// This program is free software: you can redistribute it and/or modify
/// it under the terms of the GNU General Public License as published by
/// the Free Software Foundation, either version 3 of the License, or
/// (at your option) any later version.
///
/// This program is distributed in the hope that it will be useful,
/// but WITHOUT ANY WARRANTY; without even the implied warranty of
/// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
/// GNU General Public License for more details.
///
/// You should have received a copy of the GNU General Public License
/// along with this program.  If not, see <https://www.gnu.org/licenses/>.
///

import { useEffect, useRef } from 'react'
import { apiConfig } from '../api/apiConfig'
import { useAccessToken } from '../auth/useAccessToken'

export function useWebSocket(path: string) {
  const ws = useRef<WebSocket | null>(null)
  const tokenProvider = useAccessToken()

  useEffect(() => {
    const connect = async () => {
      const token = await tokenProvider()
      const baseUrl = apiConfig.managerBaseUrl
      const wsUrl = `${baseUrl.replace(/^http/, 'ws')}${path}`
      const quarkusHeaderProtocol = encodeURIComponent("quarkus-http-upgrade#Authorization#Bearer " + token)
      const protocols = ["bearer", quarkusHeaderProtocol]

      ws.current = new WebSocket(wsUrl, protocols)

      ws.current.onopen = () => {
        console.log('WebSocket connected')
      }

      ws.current.onmessage = (event) => {
        try {
          const data = JSON.parse(event.data)
          // Dispatch toast event
          globalThis.dispatchEvent(new CustomEvent('mbyte-toast', { detail: { message: data.message || event.data } }))
        } catch (e) {
          console.error('Failed to parse WebSocket message', e)
          globalThis.dispatchEvent(new CustomEvent('mbyte-toast', { detail: { message: event.data } }))
        }
      }

      ws.current.onclose = () => {
        console.log('WebSocket disconnected')
      }

      ws.current.onerror = (error) => {
        console.error('WebSocket error', error)
      }
    }

    connect()

    return () => {
      if (ws.current) {
        ws.current.close()
      }
    }
  }, [path, tokenProvider])

  return ws.current
}
