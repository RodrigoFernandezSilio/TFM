import { Routes } from '@angular/router';
import { BuscarPartidaComponent } from './buscar-partida/buscar-partida.component';
import { CrearPartidaComponent } from './crear-partida/crear-partida.component';
import { InicioComponent } from './inicio/inicio.component';
import { MenuPrincipalComponent } from './menu-principal/menu-principal.component';
import { PartidaComponent } from './partida/partida.component';

export const routes: Routes = [
    { path: '', redirectTo: '/inicio', pathMatch: 'full' },
    { path: 'inicio', component: InicioComponent },
    { path: 'menu-principal', component: MenuPrincipalComponent },
    { path: 'buscar-partida', component: BuscarPartidaComponent },
    { path: 'crear-partida', component: CrearPartidaComponent },
    { path: 'partida/:id', component: PartidaComponent },
];
