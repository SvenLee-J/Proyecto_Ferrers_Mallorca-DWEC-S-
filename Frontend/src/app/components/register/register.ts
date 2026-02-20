import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Auth, RegisterRequest, MessageResponse } from '../../services/auth';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class RegisterComponent {
  registerForm: FormGroup; // Formulario reactivo de registro.
  errorMessage = ''; // Mensaje de error del registro.
  successMessage = ''; // Mensaje de exito del registro.
  loading = false; // Estado de carga (spinner).

  constructor(
    private fb: FormBuilder, // Crea formularios reactivos.
    private authService: Auth, // Servicio de autenticacion.
    private router: Router // Para redirecciones.
  ) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]], // Email obligatorio + formato valido.
      password: ['', [Validators.required, Validators.minLength(6)]], // Password obligatorio + minimo 6 chars.
      name: ['', Validators.required], // Nombre obligatorio.
      role: ['CLIENT', Validators.required] // Rol por defecto CLIENT.
    });
  }

  onSubmit(): void {
    if (this.registerForm.valid) { // Solo si formulario es valido.
      this.loading = true; // Muestra spinner.
      this.errorMessage = ''; // Limpia errores anteriores.
      this.successMessage = ''; // Limpia mensajes anteriores.
      
      const registerRequest: RegisterRequest = this.registerForm.value; // Obtiene datos del form.
      
      this.authService.register(registerRequest).subscribe({ // Llama API register.
        next: (response: MessageResponse) => {
          this.loading = false; // Oculta spinner.
          this.successMessage = response.message; // Muestra "User registered successfully!".
          setTimeout(() => this.router.navigate(['/login'])); // Redirige a login.
        },
        error: (error) => {
          this.loading = false; // Oculta spinner.
          this.errorMessage = error.error?.message || 'Error en el registro'; // Muestra error.
        }
      });
    }
  }
}
