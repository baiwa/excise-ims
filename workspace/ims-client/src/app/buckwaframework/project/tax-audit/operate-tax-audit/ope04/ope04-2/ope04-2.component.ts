import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ope04-2',
  templateUrl: './ope04-2.component.html',
  styleUrls: ['./ope04-2.component.css']
})
export class Ope042Component implements OnInit {

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
