import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cop06-2',
  templateUrl: './cop06-2.component.html',
  styleUrls: ['./cop06-2.component.css']
})
export class Cop062Component implements OnInit {
  showData: boolean = false;

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
