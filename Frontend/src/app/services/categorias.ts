import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Categoria {
  id: number;
  nombre: string;
}

// servicio para gestionar categorías
@Injectable({
  providedIn: 'root'
})
export class Categorias {
  // endpoint de categorías
  private readonly apiUrl = 'http://localhost:8080/api/categorias';

  // inyección de dependencias
  constructor(private http: HttpClient) {}

  // obtiene lista de categorías
  getCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.apiUrl); // sin headers
  }
}
