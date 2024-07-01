import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

interface Categoria {
  id: number;
  nombre: string;
  clasificacion: string;
  formato: string;
  idioma: string;
  año: string;
}

interface Proveedor {
  id: number;
  nombre: string;
  entrega: string;
  tipo: string;
}

interface Libro {
  id: number;
  titulo: string;
  autor: string;
  stock: number;
  precio: number;
  categoria: Categoria;
  provedores: Proveedor;
}

@Component({
  selector: 'app-books',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './books.component.html',
  styleUrl: './books.component.css'
})
export class BooksComponent implements OnInit {
    books: Libro[] = [];

    constructor(private http: HttpClient) {}

    ngOnInit() {
      this.http.get<Libro[]>('http://localhost:8095/libros')
        .subscribe((data: Libro[]) => {
          this.books = data;
        });
    }
    addToCart(book: Libro) {
      // Aquí puedes agregar la lógica para agregar el libro al carrito
      // Esto puede ser una llamada a un servicio que maneja el carrito
      console.log('Libro agregado al carrito:', book);
    }
}
