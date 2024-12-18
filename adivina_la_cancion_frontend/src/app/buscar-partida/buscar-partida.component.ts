import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PartidaService } from '../partida.service';
import { UsuarioService } from '../usuario.service';
import { Partida } from '../partida';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-buscar-partida',
  standalone: true,
  imports: [NgFor],
  templateUrl: './buscar-partida.component.html',
  styleUrl: './buscar-partida.component.css'
})
export class BuscarPartidaComponent {

  partidas: Partida[] = [];

  constructor(private router: Router, private partidaService: PartidaService, private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.partidaService.obtenerPartidas().subscribe(partidas => {
      this.partidas = partidas;
    });
  }

  anhadirUsuario(partidaID: number) {
    this.partidaService.anhadirUsuario(partidaID, this.usuarioService.usuario!.id).subscribe(() => {
      this.router.navigate([`/partida/${partidaID}`]);
    });
  }
}
