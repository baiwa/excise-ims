import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Int0611301Component } from './int06-11-3-01.component';

describe('Int0611301Component', () => {
  let component: Int0611301Component;
  let fixture: ComponentFixture<Int0611301Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Int0611301Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Int0611301Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
