mod network;
use network::server::connect;

mod basic;
use basic::primitives::primitives::sample_primitives;

fn main() {
    connect();

    sample_primitives();
}