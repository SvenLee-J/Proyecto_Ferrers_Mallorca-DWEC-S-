// sección imports
import { Component, OnInit } from '@angular/core'; // núcleo angular
import { CommonModule } from '@angular/common'; // directivas ngif y ngfor
import { HttpClientModule } from '@angular/common/http'; // módulo http para peticiones
import { Auth } from '../../services/auth'; // servicio de autenticación jwt

// interfaz de usuario
interface User {
  id: number;
  email: string;
  name: string;
  role: string;
}

// definición del componente users
@Component({
  selector: 'app-users', // selector html del componente
  standalone: true, // componente autónomo
  imports: [CommonModule, HttpClientModule], // módulos necesarios
  templateUrl: './users.html', // plantilla html asociada
  styleUrls: ['./users.css'] // estilos asociados
})
export class UsersComponent implements OnInit {

  users: User[] = []; // lista de usuarios cargados desde el backend
  loading = true; // controla el estado de carga para mostrar spinner
  error = ''; // guarda el mensaje de error en caso de fallo

  constructor(private auth: Auth) {} // inyección del servicio de autenticación

  ngOnInit(): void {
    this.loadUsers(); // carga usuarios al iniciar el componente
  }

  private loadUsers(): void {
    this.loading = true; // activa spinner de carga

    fetch('http://localhost:8080/api/users', { // petición get al endpoint protegido /api/users
      headers: {
        'Authorization': `Bearer ${this.auth.getToken()}`, // añade token jwt a la cabecera
        'Content-Type': 'application/json' // define tipo de contenido
      }
    })
      .then(response => {
        if (!response.ok) throw new Error('error cargando usuarios'); // valida respuesta http
        return response.json(); // convierte respuesta a json
      })
      .then(data => {
        this.users = data; // asigna los usuarios al array local
        this.loading = false; // desactiva spinner al completar carga
      })
      .catch(error => {
        this.error = error.message; // guarda mensaje de error en caso de fallo
        this.loading = false; // desactiva spinner tras el error
      });
  }
}
