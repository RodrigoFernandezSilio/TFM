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
    console.log(`Obteniendo partidas...`)

    const url = this.partidasURL;

    return this.http.get<Partida[]>(url).pipe(
      tap(_ => {
        console.log(`Partidas obtenidas`)
      })
    );
  }

  crearPartida(partidaDTO: PartidaDTO): Observable<number> {
    console.log("Creando partida...");

    const url = `${this.partidasURL}`;

    return this.http.post<number>(url, partidaDTO).pipe(
      tap(_ => {
        console.log(`Partida creada`)
      })
    );
  }

  anhadirUsuario(partidaID: number, usuarioID: number): Observable<any> {
    console.log(`Añadiendo usuario ${usuarioID} a partida ${partidaID}...`);

    const url = `${this.partidasURL}/${partidaID}/${usuarioID}/anhadirUsuario`;

    return this.http.put(url, {}).pipe(
      tap(_ => {
        console.log(`Usuario anhadido`)
      })
    );
  }

  iniciarPartida(partidaID: number, usuarioID: number) {
    console.log(`Iniciando partida ${partidaID} por el usuario ${usuarioID}...`);
  
    const url = `${this.partidasURL}/${partidaID}/${usuarioID}/iniciarPartida`;
  
    return this.http.put(url, {}).pipe(
      tap(() => {
        console.log(`Partida ${partidaID} iniciada por el usuario ${usuarioID}.`);
      })
    );
  }  
}
