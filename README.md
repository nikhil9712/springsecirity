# springsecirity
Learning Spring Seciruty


When we send login and permitted based on role then internally seciruty 
checks the role of user (by name and password) weather present in 
security context or not if not returns 403

if we directly allowes the API and with matcher then security does not skips 
the context check

“If API is permitted, Spring should skip checking authentication.”

No — permitAll only skips authorization, NOT authentication filters.

Request → Security Filters → (1) Authentication → SecurityContext
                                          ↓
                                (2) Authorization → Check roles
                                          ↓
                                  ALLOW or DENY


         ┌────────────────────────────────┐
         │           LOGIN (/login)       │
         └────────────────────────────────┘
                    ↓
          JWT Filter = SKIPPED ❌
                    ↓
           NO AUTHENTICATION ❌
           NO AUTHORIZATION  ❌
                    ↓
          Controller verifies username/password
                    ↓
               Generate Token
                    ↓
            Return Token to Client
──────────────────────────────────────────────
         Next Request with Token
──────────────────────────────────────────────
                    ↓
          JWT Filter = RUN ✔
                    ↓
           AUTHENTICATION ✔ (token check)
                    ↓
           AUTHORIZATION ✔ (role check)
                    ↓
              ALLOW / DENY
