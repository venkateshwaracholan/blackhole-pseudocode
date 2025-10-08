package java_behaviours;

/**
 * RECORD vs CLASS in Java — Detailed Documentation
 * -------------------------------------------------
 * 🔹 1. PURPOSE AND DESIGN INTENT:
 *    - record:
 *        ➤ Introduced in Java 14 (preview) / Java 16 (stable).
 *        ➤ Designed as a *data carrier* — a transparent, immutable holder of state.
 *        ➤ Automatically provides canonical constructor, equals(), hashCode(), and toString().
 *        ➤ Encourages declarative, boilerplate-free modeling of plain data.
 *
 *    - class:
 *        ➤ The general-purpose, flexible blueprint for objects.
 *        ➤ Can define mutable fields, custom constructors, and override any behavior.
 *        ➤ Suitable for complex, evolving, or high-performance systems where control matters.
 *
 * -------------------------------------------------
 * 🔹 2. IMMUTABILITY & STATE HANDLING:
 *    - record fields are *final and private* by default.
 *      → Once constructed, state cannot be changed.
 *      → Enforces strong immutability guarantees, thread safety by design.
 *    - class allows mutable or immutable design, based on developer intent.
 *      → Can have setters, reassignable fields, and support in-place updates.
 *
 * -------------------------------------------------
 * 🔹 3. PERFORMANCE CHARACTERISTICS:
 *    - record:
 *        → Immutability implies new object creation for every state change.
 *        → Leads to frequent allocations and higher garbage collection in tight loops.
 *        → Example: In BFS or DFS traversals, each node-depth pair becomes a new record object.
 *        → This causes minor CPU and memory overhead, especially with large trees or graphs.
 *
 *    - class:
 *        → Allows object reuse (mutable), fewer allocations, and lower GC pressure.
 *        → More efficient in data structure traversals, streaming, and iterative workloads.
 *        → Particularly advantageous in problems like Level Order Traversal, Graph BFS, etc.
 *
 * -------------------------------------------------
 * 🔹 4. INHERITANCE AND EXTENSIBILITY:
 *    - record:
 *        → Implicitly extends java.lang.Record (cannot extend any other class).
 *        → Can implement interfaces but not extend other user-defined classes.
 *        → Limited flexibility for future expansion.
 *
 *    - class:
 *        → Full inheritance and polymorphism support.
 *        → Can extend, override, or compose freely.
 *        → Ideal for systems expected to evolve or require hierarchy.
 *
 * -------------------------------------------------
 * 🔹 5. READABILITY AND EXPRESSIVENESS:
 *    - record:
 *        → Cleaner and more readable for small, static data models (e.g., DTOs, API responses, configs).
 *        → Self-documenting — fields declared in the header define the canonical form.
 *    - class:
 *        → Slightly more verbose but explicit.
 *        → Allows custom behavior, input validation, and encapsulation logic.
 *
 * -------------------------------------------------
 * 🔹 6. WHEN TO USE WHAT:
 *    ✅ Use record:
 *        - When the entity is a simple, immutable data holder.
 *        - For request/response models, configuration objects, or mappings where immutability is desired.
 *        - When clarity and conciseness outweigh micro-optimization.
 *
 *    ✅ Use class:
 *        - Inside algorithms (e.g., BFS, DFS) or any performance-critical loop.
 *        - When mutability, reusability, or inheritance is required.
 *        - For complex systems with non-trivial state transitions.
 *
 * -------------------------------------------------
 * 🔹 7. INTERVIEW INSIGHT (FAANG CONTEXT):
 *    - Using record in solutions like Level Order Traversal is acceptable if you explain:
 *        → “I used record for readability and immutability; in production, I’d replace it with a class 
 *           to minimize object creation overhead inside loops.”
 *    - This shows both practical reasoning and awareness of performance trade-offs.
 *    - Recruiters value understanding *why* you pick one construct over another — not just the syntax.
 *
 * -------------------------------------------------
 * 🔹 8. SUMMARY (ONE-LINER):
 *    - record = concise, immutable, declarative data carrier.
 *    - class  = flexible, mutable, performant object model.
 *    - Choose based on trade-off between readability and performance.
 */
public class RecordVsClass {
}
