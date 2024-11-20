import { Component } from '@angular/core';
import { UsuarioService } from '../usuario.service';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent {

  constructor(private usuarioService: UsuarioService) { }

  crearUsuario(nombreUsuario: string) {
    console.log("Creando usuario...");
    this.usuarioService.crearUsuario(nombreUsuario).subscribe({
      next: () => {
        console.log(`Usuario ${nombreUsuario} creado en el componente`);
      },
      error: (error) => {
        console.error("Error al crear usuario:", error);
      }
    });
  }
  
}
