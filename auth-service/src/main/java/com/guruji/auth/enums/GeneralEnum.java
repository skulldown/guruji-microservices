package com.guruji.auth.enums;

public enum GeneralEnum {
  LRU("LRU"),
  CODE("code"),
  TOKEN("token"),
  BROWSER("browser"),
  DEVICEOS("deviceos"),
  AUTHORIZATION("Authorization"),
  TRACE_ID("traceId"),
  ACCESS_CONTROL_EXPOSE_HEADERS("Access-Control-Expose-Headers"),
  AUTH("auth"),
  SECRET(
    "MIICXQIBAAKBgQCmsl5foklPotqyGVCjqpf5zVMntGQBuvEQpJE9Vx0FZLLm9ERWQeQftE3vtjy3kOamHhn8D2xBvVRnBZKxa3JYvDVOae4JbzByfuuquC0nrSOlSYFb3vex42FwZ3y1Wuwv3JrtO1z2P0sR/+uggPnbxWi3WalPYx52cJ5+0aoWSQIDAQABAoGBAJTrfIwLO1s9rgq1uui8GpE78THjYgR5saqgmnAmRvLuhzT0u6gjrVz1gzrHn26OsL9u3N/mF1yTe7edrvN5feh44kzeQVxIaOcfllS5GN5EJmLyLMdHu01lo8pj/iaN7cUgC28DPXNWC3akwbyCPMozGkJ33MByBknBW0INe6IRAkEA98hDApriJdEQCi+41f/pJuVzab5WM6ihhswJsFkULsWR1phEkcFaFFk9SvVmRoV7jIAerZQuV179Dd6px1fYxwJBAKw5q0JS6j0xxd3XnNClFoU+QFF7hFixUT7CEgM8R3vZ269c5LqNtjZjaE2qKILbcSumMqBwClTy5T9l1OWsKG8CQDu2xX9xOCMI+SabTZ41KEk5btEDD98vOo4zyfn33cdP0c+3rn657C2PPBg+f8FrFdVMYxhN58UinTWkfaIO560CQEPhqw3GLosWHh4VSLq4BwtXgPRc0co/gX1R8tWRob9AHCWfYIvjJSmEvuy1n6gMJCwHIrc30eqvngoB8grgc0MCQQCfnIpOZFYxFKXQ0WoVOgV3YJ+rbqWMYrZ8Ak38tkDAfFm9A6ZRQK20k85fEhNgmmi5JdJOIGFAj1pLxPWUCAXw"),
  ACCESS_TOKEN("access_token"),
  EMPTY_STRING(""),
  BEARER("Bearer "),
  ACCESS_DENIED("Access is Denied"),
  ;

  private String value;

  GeneralEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
