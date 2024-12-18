import { Usuario } from "./usuario";

export interface Partida {
    id: number;
    estado: EstadoPartida;
    usuarios: Usuario[];
    numMaxRondas: number;
    rondas: Ronda[];
    playlist: Playlist;
    privada: boolean;
    codigoAcceso: string;
}

enum EstadoPartida {
	NO_INICIADA,
    INICIADA,
    FINALIZADA
}

interface Ronda {
    id: number;
    canciones: Cancion[];
    cancionCorrecta: Cancion;
}

interface Playlist {
    id: number;
    nombre: string;
}

interface Cancion {
    id: number;
    nombre: string;
    audioURL: string;
}



export interface PartidaDTO {
    numMaxUsuarios: number;
    usuarioID: number;
    numMaxRondas: number;
    playlistID: number;
    privada: boolean;
    codigoAcceso: string;
}