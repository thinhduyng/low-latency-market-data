# Project Specification: Low-Latency Market Data Component

## Project Overview

The objective of this project is to implement a low-latency market data processing component in Java using a real-world exchange market data protocol.

The system shall consume recorded binary market data conforming to the **Nasdaq TotalView-ITCH 5.0** specification, decode relevant market events, maintain the state of individual orders, and reconstruct the corresponding limit order book.

The project is intended to demonstrate competency in binary protocol processing, efficient in-memory data structures, correctness under event-driven state transitions, and performance-conscious Java programming.

## Project Goals

The completed system shall be capable of:

- Reading and decoding binary Nasdaq TotalView-ITCH 5.0 market data.
- Correctly interpreting the message types required to maintain order-book state.
- Tracking the lifecycle of individual orders.
- Maintaining aggregated state at each price level.
- Maintaining order count and available share quantity at each price level.
- Determining the current best bid and best ask for supported securities.
- Reconstructing market state deterministically from an ordered stream of market-data events.
- Processing realistic market-data samples with high throughput and predictable latency.
- Producing reproducible performance measurements for the implemented system.

## Functional Requirements

At minimum, the implementation must correctly support the market events necessary for order-book reconstruction, including:

- Add Order
- Add Order with MPID Attribution
- Order Executed
- Order Executed with Price
- Order Cancel
- Order Delete
- Order Replace

For each tracked security, the reconstructed order book must be able to represent:

- Active orders
- Bid and ask price levels
- Number of active orders at each price level
- Aggregate share quantity at each price level
- Best bid price and available quantity
- Best ask price and available quantity

Order executions, partial executions, cancellations, deletions, and replacements must update the resulting market state correctly.

## Technical Requirements

The project shall:

- Be implemented primarily in Java.
- Use the Nasdaq TotalView-ITCH 5.0 binary protocol as the market-data format.
- Operate on recorded or sample market data rather than requiring a live exchange connection.
- Preserve numeric precision required by the protocol.
- Correctly handle the byte ordering, field widths, timestamps, identifiers, and numeric representations defined by the protocol.
- Avoid unnecessary dependencies on application frameworks where they do not contribute to the core problem.
- Keep the market-data processing path suitable for performance analysis.

## Correctness Requirements

Correctness is a primary requirement.

The implementation must preserve the semantics of the source event stream and maintain internally consistent order-book state. In particular:

- Orders must not be created, modified, or removed incorrectly.
- Partial executions and cancellations must preserve remaining quantity.
- Replacements must preserve the semantics defined by the protocol.
- Price-level order counts and aggregate quantities must remain consistent with the underlying active orders.
- Best bid and best ask values must reflect the current reconstructed book.

Automated tests should demonstrate correctness for both individual message handling and sequences of order lifecycle events.

## Performance Requirements

The project must include quantitative performance evaluation.

Relevant measurements include:

- Message-processing throughput
- Per-message processing latency
- Latency percentiles such as p50, p99, and p99.9
- Memory consumption
- Allocation rate
- Garbage-collection activity

Performance results should be reproducible and reported together with sufficient environment information to make the measurements meaningful.

Optimization claims must be supported by measurements.

## Evaluation Criteria

The project should be evaluated based on:

### Protocol Correctness
Accuracy of binary decoding and compliance with the relevant portions of the Nasdaq TotalView-ITCH 5.0 specification.

### Order-Book Correctness
Accuracy of reconstructed order state, price levels, quantities, and best bid/ask values.

### Software Design
Clarity of component boundaries, data representations, interfaces, and separation between protocol decoding and market-state management.

### Performance Engineering
Evidence of deliberate attention to latency, throughput, memory allocation, garbage collection, and data-structure efficiency.

### Benchmark Quality
Reproducibility, methodology, meaningful workloads, and appropriate interpretation of performance results.

### Code Quality
Readability, maintainability, testing discipline, and appropriate use of Java language and standard-library features.

### Technical Communication
Quality of documentation explaining the system, its behavior, design decisions, limitations, and measured results.

## Expected Deliverables

The repository should contain:

- Source code for the market-data decoder.
- Source code for order tracking and order-book reconstruction.
- Automated correctness tests.
- Performance benchmarks.
- Benchmark results.
- Documentation describing the implemented system and its supported protocol behavior.
- Instructions sufficient to build, test, and execute the project.

A deployed web service, graphical interface, trading strategy, database, or live exchange connection is **not** required.

## Scope

The project is specifically concerned with **market-data processing and order-book reconstruction**.

The following are outside the required scope:

- Order entry
- Trade execution
- Matching-engine implementation
- Trading strategies
- Profitability analysis
- FIX order routing
- User interfaces
- Persistent databases
- Web APIs
- Production deployment
- Live exchange connectivity

Additional functionality may be implemented, but it should not compromise the correctness, clarity, or performance focus of the core project.
