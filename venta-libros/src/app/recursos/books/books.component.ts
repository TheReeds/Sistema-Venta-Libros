import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Libro } from '../libro.model';
import { FormsModule } from '@angular/forms';
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
      .subscribe(
        (data: Libro[]) => {
          this.books = data;
        },
        (error) => {
          console.error('Error al obtener los libros:', error);
        }
      );
  }
  getImageUrl(imagePath: string): string {
    return `http://localhost:8095/libros/uploads/${imagePath}`;
  }

  addToCart(book: Libro) {
    console.log('Libro agregado al carrito:', book);
  }
}
