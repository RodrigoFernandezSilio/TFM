import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Usuario } from './usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  usuariosURL = "http://localhost:8080/usuarios"

  usuario: Usuario | null = null;

  constructor(private http: HttpClient) { }

  crearUsuario(nombreUsuario: string): Observable<number> {
    console.log("Creando usuario...")

    const url = `${this.usuariosURL}/${nombreUsuario}`;

    return this.http.post<number>(url, null).pipe(
      tap((usuarioID) => {
        this.usuario = {
          id: usuarioID,
          nombre: nombreUsuario,
        };
        console.log(`Usuario creado`);
      })
    );
  }
}
