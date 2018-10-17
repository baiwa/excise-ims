import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'ope05-7',
  templateUrl: './ope05-7.component.html',
  styleUrls: ['./ope05-7.component.css']
})
export class Ope057Component implements OnInit {

  public showData: boolean = false;

  constructor(
    private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-05700');
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}
