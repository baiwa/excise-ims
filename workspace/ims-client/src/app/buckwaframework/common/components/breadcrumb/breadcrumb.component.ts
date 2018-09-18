import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styles: [
    `
    .sixteen.wide.column.bread {
      padding-top: 0;
    }
    .shadow {
      background-color: #fff;
      box-shadow: 2px 5px 9px rgba(0, 0, 0, 0.1);
    }
    `
  ]
})
export class BreadcrumbComponent {
  constructor() {
  }
  @Input() route: any = [
    {  }
  ];
}
