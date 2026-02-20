import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Auth } from '../services/auth';
import { Router } from '@angular/router';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const auth = inject(Auth); // Inyecta servicio Auth.
  const router = inject(Router); // Inyecta Router.

  // Skip auth endpoints (login/register no necesitan token).
  const isAuthEndpoint = req.url.includes('/auth/');
  if (isAuthEndpoint) {
    return next(req); // Pasa request sin tocar.
  }

  // Add Bearer token a TODAS las demas peticiones.
  const token = auth.getToken(); // Obtiene token del localStorage.
  let authReq = req;

  if (token) {
    authReq = req.clone({ // Crea copia del request con header Authorization.
      setHeaders: {
        Authorization: `Bearer ${token}` // Agrega Bearer
      }
    });
  }

  return next(authReq); // Ejecuta request con o sin token.
};
