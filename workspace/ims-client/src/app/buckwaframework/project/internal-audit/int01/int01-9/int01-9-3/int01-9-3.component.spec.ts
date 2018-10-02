import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Int0193Component } from './int01-9-3.component';

describe('Int0193Component', () => {
  let component: Int0193Component;
  let fixture: ComponentFixture<Int0193Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Int0193Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Int0193Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
