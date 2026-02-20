import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Auth, LoginRequest } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {
  loginForm: FormGroup; // Formulario reactivo de login.
  errorMessage = ''; // Mensaje de error del login.
  loading = false; // Estado de carga (spinner).

  constructor(
    private fb: FormBuilder, // Crea formularios reactivos.
    private authService: Auth, // Servicio de autenticacion.
    private router: Router // Para redirecciones.
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]], // Email obligatorio validacion.
      password: ['', [Validators.required, Validators.minLength(6)]] // Password obligatorio validacion.
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) { // Solo si formulario es valido.
      this.loading = true; // Muestra spinner.
      this.errorMessage = ''; // Limpia errores anteriores.
      
      const loginRequest: LoginRequest = this.loginForm.value; // Obtiene datos del form.
      
      this.authService.login(loginRequest).subscribe({
        next: () => {
          this.loading = false; 
          this.router.navigate(['/home']); // Redirige a home.
        },
        error: (error) => {
          this.loading = false; 
          this.errorMessage = error.error?.message || 'Error en el login'; // Muestra error.
        }
      });
    }
  }
}
