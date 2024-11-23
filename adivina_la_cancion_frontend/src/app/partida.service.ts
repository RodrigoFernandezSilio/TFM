import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Partida, PartidaDTO } from './partida';

@Injectable({
  providedIn: 'root'
})
export class PartidaService {

  partidasURL = "http://localhost:8080/partidas"

  constructor(private http: HttpClient) { }

  obtenerPartidas(): Observable<Partida[]> {
    const url = this.partidasURL;

    return this.http.get<Partida[]>(url).pipe(
      tap(_ => {
        console.log(`Partidas obtenidas`)
      })
    )
  }

  crearPartida(partidaDTO: PartidaDTO): Observable<number> {
    console.log("Creando partida...")

    const url = `${this.partidasURL}`;

    return this.http.post<number>(url, partidaDTO).pipe(
      tap(_ => {
        console.log(`Partida creada`)
      })
    )
  }
}
