import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'int05-3',
  templateUrl: './int05-3.component.html',
  styleUrls: ['./int05-3.component.css']
})
export class Int053Component implements OnInit {

  private showData: boolean = false;
  constructor() { }

  ngOnInit() {
  }

  uploadFile() {
    this.showData = true;
  }

  clearFile() {
    this.showData = false;
  }
}
