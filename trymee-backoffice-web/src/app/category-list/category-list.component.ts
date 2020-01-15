import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { CategoryService } from '../category.service';

@Component({
  moduleId: module.id,
  selector: 'category-list',
  templateUrl: 'category-list.component.html',
  styleUrls: ['category-list.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class CategoryListComponent implements OnInit {

  categories: Object[] = [];
  links: Object[] = [];
  page: Object;
  message: string;

  loadCategories(url: string): void {
    this.categoryService.list(url || null).subscribe(
      data => {
        this.categories = data['_embedded'].category;
        this.page = data['page'];
        this.processPagination(data);
      },
      err => {
        console.log(err);
        this.message = err['message'];
      }
      
    );
  }

  constructor(private categoryService: CategoryService) {}

  private processPagination(data: Object) {
    this.links = [];
    if (data['_links']['first'])
      this.links.push({ 'name': 'First', 'url': data['_links'].first.href });
    if (data['_links']['prev'])
      this.links.push({ 'name': 'Previous', 'url': data['_links'].prev.href });
    if (data['_links']['next'])
      this.links.push({ 'name': 'Next', 'url': data['_links'].next.href });
    if (data['_links']['last'])
      this.links.push({ 'name': 'Last', 'url': data['_links'].last.href });
  }

  ngOnInit() {
    this.loadCategories(null);
  }

}
