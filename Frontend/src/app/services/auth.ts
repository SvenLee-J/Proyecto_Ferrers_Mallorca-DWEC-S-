import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
  userRol() {
    throw new Error('Method not implemented.');
  }
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

  // MÉTODO 1: Rol desde localStorage (rápido)
  hasRoleStorage(role: string): boolean {
    return this.getRole() === role; // Verifica rol especifico.
  }

  // MÉTODO 2: Rol desde JWT payload (preciso)
  hasRoleJwt(role: string): boolean {
    const token = this.getToken();
    if (!token) return false;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.rol?.includes(role) || payload.role === role || false;
    } catch {
      return false;
    }
  }

  // Usa JWT para máxima precisión
  hasRole(role: string): boolean {
    return this.hasRoleJwt(role);
  }

  isAdmin(): boolean {
    return this.hasRole('ADMIN'); // Verifica si es ADMIN.
  }

  getUserIdFromToken(): number {
    const token = this.getToken();
    if (!token) return 0;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.sub || payload.userId || 0; // o el campo que uses para ID
    } catch {
      return 0;
    }
  }

  // Headers para peticiones autenticadas
  getAuthHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }
}
