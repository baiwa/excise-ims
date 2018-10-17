import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'ope05-6',
  templateUrl: './ope05-6.component.html',
  styleUrls: ['./ope05-6.component.css']
})
export class Ope056Component implements OnInit {

  public showData: boolean = false;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-05600');
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}
