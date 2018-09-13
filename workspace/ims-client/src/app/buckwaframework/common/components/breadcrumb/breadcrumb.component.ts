import { Component,Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
})
export class BreadcrumbComponent {
  constructor() {
  }
  @Input() route:any=[1,2,3,4,5,6];
}
