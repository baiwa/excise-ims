import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'create-paper-receive-instant',
  templateUrl: './create-paper-receive-instant.component.html',
  styleUrls: ['./create-paper-receive-instant.component.css']
})
export class CreatePaperReceiveInstantComponent implements OnInit {
  
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
