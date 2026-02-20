import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { Auth } from '../services/auth'; 

export const authGuard: CanActivateFn = (route, state) => {
  const auth = inject(Auth); // Inyecta servicio Auth.
  const router = inject(Router); // Inyecta Router.

  // Si usuario logueado → permite acceso.
  if (auth.isLoggedIn()) {
    return true;
  } else {
    // Si NO logueado → redirige a login.
    return router.createUrlTree(['/login']);
  }
};
