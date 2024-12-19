import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private socket: WebSocket | null = null;  // Inicializa como null
  private messages: Subject<any> = new Subject<any>();  // Subject para emitir mensajes

  constructor() {}

  connect(partidaID: number, usuarioID: number): void {
    // Cambia esta URL a la URL de tu servidor de WebSocket
    const url = `ws://localhost:8080/webSocketPartida/${partidaID}/${usuarioID}`;

    // Crea una nueva conexión WebSocket
    this.socket = new WebSocket(url);

    // Cuando se recibe un mensaje del servidor, lo emitimos a través del Subject
    this.socket.onmessage = (event) => {
      this.messages.next(JSON.parse(event.data));
    };

    // También puedes manejar eventos de error y cierre
    this.socket.onerror = (event) => {
      console.error("Error en la conexión WebSocket", event);
    };
  }

  // Método para enviar un mensaje al servidor WebSocket
  sendMessage(message: any): void {
    if (this.socket && this.socket.readyState === WebSocket.OPEN) {
      this.socket.send(JSON.stringify(message));
    }
  }

  // Obtener los mensajes recibidos del servidor
  getMessages() {
    return this.messages.asObservable();  // Retorna el Subject como un Observable
  }

  // Cerrar la conexión WebSocket
  disconnect() {
    if (this.socket) {
      this.socket.close();
    }
  }
}
