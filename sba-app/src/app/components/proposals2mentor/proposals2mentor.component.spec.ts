import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Proposals2mentorComponent } from './proposals2mentor.component';

describe('Proposals2mentorComponent', () => {
  let component: Proposals2mentorComponent;
  let fixture: ComponentFixture<Proposals2mentorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Proposals2mentorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Proposals2mentorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
