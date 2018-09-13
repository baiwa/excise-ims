import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styles: [
    `
    .sixteen.wide.column.bread {
      padding-top: 0;
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
