import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { of } from 'rxjs/observable/of';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class CategoryService {

  private categoriesUrl = 'http://localhost:8080/api/categories';

  list(url: string): Observable<Object> {
    return this.http.get<Object>(url || this.categoriesUrl);
  }

  loadPage(url:string): Observable<Object> {
    return this.http.get<Object>(url);
  }

  constructor(private http: HttpClient) {



  }

}
