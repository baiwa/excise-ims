import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'ope05-1',
  templateUrl: './ope05-1.component.html',
  styleUrls: ['./ope05-1.component.css']
})
export class Ope051Component implements OnInit {

  public showData: boolean = false;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-05100');
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}
