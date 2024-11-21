import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from '../usuario.service';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent {

  constructor(private router : Router, private usuarioService: UsuarioService) { }

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

    this.router.navigate(['/menu-principal']); // Redirige a /menu-principal
  }
  
}
