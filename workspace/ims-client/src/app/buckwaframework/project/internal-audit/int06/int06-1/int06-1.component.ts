import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'int06-1',
  templateUrl: './int06-1.component.html',
  styleUrls: ['./int06-1.component.css']
})
export class Int061Component implements OnInit {

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
