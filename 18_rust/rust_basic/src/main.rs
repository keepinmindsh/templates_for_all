mod network;
use network::server::connect;

mod basic;
use basic::primitives::primitives;
use basic::literal_operator::literal_operator;

fn main() {
    //connect();

    primitives::sample_primitives();

    println!("-----------------------------------\r\n");

    literal_operator::literal_operator();

}