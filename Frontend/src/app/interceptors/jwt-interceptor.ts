import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Auth } from '../services/auth';
import { Router } from '@angular/router';

// interceptor para añadir JWT token automáticamente
export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const auth = inject(Auth);
  const router = inject(Router);

  // endpoints de auth sin token
  const isAuthEndpoint = req.url.includes('/auth/');
  if (isAuthEndpoint) {
    return next(req);
  }

  // añade token a todas las demás peticiones
  const token = auth.getToken();
  let authReq = req;

  if (token) {
    authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  return next(authReq);
};
