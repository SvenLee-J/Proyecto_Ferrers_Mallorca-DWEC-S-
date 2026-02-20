import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Router } from '@angular/router';

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  email: string;
  password: string;
  name: string;
  role: string;
}

export interface JwtResponse {
  token: string;
  type: string;
  email: string;
  role: string;
}

export interface MessageResponse {
  message: string;
}

@Injectable({
  providedIn: 'root' // Servicio global singleton.
})
export class Auth {
  private readonly apiUrl = 'http://localhost:8080/auth'; // URL del backend Spring Boot.

  constructor(
    private http: HttpClient, // Cliente HTTP de Angular.
    private router: Router // Para redirecciones.
  ) {}

  login(loginRequest: LoginRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.apiUrl}/login`, loginRequest)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token); // Guarda token JWT.
          localStorage.setItem('email', response.email); // Guarda email usuario.
          localStorage.setItem('role', response.role); // Guarda rol usuario.
        })
      );
  }

  register(registerRequest: RegisterRequest): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(`${this.apiUrl}/register`, registerRequest);
  }

  logout(): void {
    localStorage.removeItem('token'); // Borra token.
    localStorage.removeItem('email'); // Borra email.
    localStorage.removeItem('role'); // Borra rol.
    this.router.navigate(['/login']); // Redirige a login.
  }

  getToken(): string | null {
    return localStorage.getItem('token'); // Obtiene token guardado.
  }

  getEmail(): string | null {
    return localStorage.getItem('email'); // Obtiene email guardado.
  }

  getRole(): string | null {
    return localStorage.getItem('role'); // Obtiene rol guardado.
  }

  isLoggedIn(): boolean {
    return !!this.getToken(); // Verifica si hay token (usuario logueado).
  }

  hasRole(role: string): boolean {
    return this.getRole() === role; // Verifica rol especifico.
  }

  isAdmin(): boolean {
    return this.hasRole('ADMIN'); // Verifica si es ADMIN.
  }
}
