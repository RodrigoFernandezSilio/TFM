import { Routes } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
import { MenuPrincipalComponent } from './menu-principal/menu-principal.component';

export const routes: Routes = [
    { path: '', redirectTo: '/inicio', pathMatch: 'full' },
    { path: 'inicio', component: InicioComponent },
    { path: 'menu-principal', component: MenuPrincipalComponent },
];
