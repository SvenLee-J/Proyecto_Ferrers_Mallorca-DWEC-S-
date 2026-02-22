import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { Auth } from '../services/auth';

// guard para proteger rutas de ferrer
export const authGuard: CanActivateFn = (route, state) => {
  const auth = inject(Auth);
  const router = inject(Router);

  // verifica autenticación y rol FERRER
  if (auth.isLoggedIn() && auth.hasRole('FERRER')) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
