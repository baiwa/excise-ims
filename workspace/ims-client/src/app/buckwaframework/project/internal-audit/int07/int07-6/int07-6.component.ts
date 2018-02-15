import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'int07-6',
  templateUrl: './int07-6.component.html',
  styleUrls: ['./int07-6.component.css']
})
export class Int076Component implements OnInit {

  private showData: boolean = false;

  constructor() { }

  ngOnInit() {
  }
 
  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }
}
