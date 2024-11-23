import { Component } from '@angular/core';
import { Partida, PartidaDTO } from '../partida';
import { PartidaService } from '../partida.service';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../usuario.service';

@Component({
  selector: 'app-crear-partida',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './crear-partida.component.html',
  styleUrl: './crear-partida.component.css'
})
export class CrearPartidaComponent {

  partidaDTO: PartidaDTO = {
    numMaxUsuarios: 0,
    usuarioID: 0,
    numMaxRondas: 0,
    playlistID: 0,
    privada: false,
    codigoAcceso: '',
  };

  constructor(private partidaService: PartidaService, private usuarioService: UsuarioService) { }

  ngOnInit() {
    this.partidaDTO.usuarioID = this.usuarioService.usuario!.id;
  }

  crearPartida() {
    this.partidaService.crearPartida(this.partidaDTO).subscribe(partidaID => {
      this.partidaService.anhadirUsuario(partidaID, this.usuarioService.usuario!.id);
    });
  }

}
