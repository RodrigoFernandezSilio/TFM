import { NgFor } from '@angular/common';
import { Component, OnDestroy } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { WebsocketService } from '../websocket.service';
import { UsuarioService } from '../usuario.service';

@Component({
  selector: 'app-partida',
  standalone: true,
  imports: [NgFor, FormsModule],
  templateUrl: './partida.component.html',
  styleUrls: ['./partida.component.css']
})
export class PartidaComponent implements OnDestroy {
  partidaID!: number;  // "!" indica que esta propiedad sera asignada mas tarde
  usuarioID!: number
  mensajes: any[] = [];
  mensajeEnviado: string = '';  // Mensaje que el usuario está escribiendo

  constructor(private route: ActivatedRoute, private websocketService: WebsocketService, private usuarioService: UsuarioService) {
    // Obtén el ID de la partida desde la URL
    this.route.params.subscribe(params => {
      this.partidaID = +params['id'];  // Convierte a número
      this.usuarioID = this.usuarioService.usuario!.id
      this.websocketService.connect(this.partidaID, this.usuarioID);  // Conéctate a WebSocket para la partida

      // Suscríbete a los mensajes que llegan por WebSocket
      this.websocketService.getMessages().subscribe(message => {
        this.mensajes.push(message);  // Maneja los mensajes recibidos
      });
    });
  }

  // Método para enviar mensajes a través del WebSocket
  enviarMensaje() {
    let mensajeEnviadoSinEspacios = this.mensajeEnviado.trim(); // Elimimar espacios en blanco al inicio y al final
    // Comprobar que el mensaje no este vacio
    if (mensajeEnviadoSinEspacios) {
      this.websocketService.sendMessage({ text: this.mensajeEnviado });
      this.mensajeEnviado = '';  // Limpiar el area de texto despues de enviar el mensaje
    }
    console.log(this.mensajes)
  }
  

  ngOnDestroy() {
    // Cierra la conexión WebSocket cuando se destruye el componente
    this.websocketService.disconnect();
  }
}