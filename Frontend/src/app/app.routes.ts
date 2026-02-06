import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Login } from './login/login';
import { Register } from './register/register';


export const routes: Routes = [

    // Redirecciona la pagina principal a /home. 
    // sin "full" Cualquier path conduceria a /home y habria un bucle que te llevara a /home.
    // "pathMatch: 'full' " Indica que debe cumplir exactamente /'' para que te redireccione a /home. 
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: Home },
    { path: 'login', component: Login },
    { path: 'register', component: Register },

];
