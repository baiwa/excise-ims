import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ope04-9',
  templateUrl: './ope04-9.component.html',
  styleUrls: ['./ope04-9.component.css']
})
export class Ope049Component implements OnInit {
  
  public showData: boolean = false;

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
