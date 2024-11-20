import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  usuariosURL = "http://localhost:8080/usuarios"

  constructor(private http: HttpClient) { }

  crearUsuario(nombreUsuario: string): Observable<any> {
    console.log("Creando usuario...")

    const url = `${this.usuariosURL}/${nombreUsuario}`;

    return this.http.post<any>(url, null).pipe(
      tap(_ => {
        console.log(`Usuario ${nombreUsuario} creado`);
      })
    )
  }
}
