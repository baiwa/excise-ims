import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'ope05-3',
  templateUrl: './ope05-3.component.html',
  styleUrls: ['./ope05-3.component.css']
})
export class Ope053Component implements OnInit {

  public showData: boolean = false;

  constructor(
    private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-05300');
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}
