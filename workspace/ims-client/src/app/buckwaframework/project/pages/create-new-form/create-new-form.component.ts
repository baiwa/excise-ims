import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-new-form',
  templateUrl: './create-new-form.component.html',
  styleUrls: ['./create-new-form.component.css']
})
export class CreateNewFormComponent implements OnInit {

  public showUploadDetail:boolean = false;
  
  constructor() { }

  ngOnInit() {
  }

  onClickUpload() {
    this.showUploadDetail = true;
  }

  onclickDelete() {
    this.showUploadDetail = false;
  }
}
