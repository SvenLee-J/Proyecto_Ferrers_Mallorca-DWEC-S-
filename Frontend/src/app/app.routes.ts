// sección imports
import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home';
import { LoginComponent } from './components/login/login';
import { RegisterComponent } from './components/register/register';

export const routes: Routes = [
  // redirige raíz a home
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  
  { path: 'home', component: HomeComponent },     // home principal
  { path: 'login', component: LoginComponent },   // login usuario
  { path: 'register', component: RegisterComponent }, // registro nuevo usuario
  
  // ruta no encontrada → home
  { path: '**', redirectTo: '/home' }
];
